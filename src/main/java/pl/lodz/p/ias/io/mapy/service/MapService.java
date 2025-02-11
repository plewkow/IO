package pl.lodz.p.ias.io.mapy.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.type.MapType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.ias.io.mapy.model.MapPoint;
import pl.lodz.p.ias.io.mapy.model.PointType;
import pl.lodz.p.ias.io.mapy.repository.MapPointRepository;
import pl.lodz.p.ias.io.powiadomienia.Interfaces.INotificationService;
import pl.lodz.p.ias.io.powiadomienia.notification.NotificationType;
import pl.lodz.p.ias.io.uwierzytelnianie.model.Role;
import pl.lodz.p.ias.io.uwierzytelnianie.repositories.AccountRepository;
import pl.lodz.p.ias.io.zasoby.model.Warehouse;
import pl.lodz.p.ias.io.zasoby.repository.WarehouseRepository;
import pl.lodz.p.ias.io.zasoby.service.WarehouseService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class MapService implements IMapService {

    @Autowired
    INotificationService notificationService;

    @Autowired
    WarehouseRepository warehouseRepository;

    private MapPointRepository mapPointRepository;

    @Override
    public MapPoint addPoint(MapPoint point) {
        mapPointRepository.save(point);
        Role role = new Role("WOLONTARIUSZ");
        List<MapPoint> volunteers_points = getPointsByType(PointType.VOLUNTEER);
        for (MapPoint mapPoint : volunteers_points) {
            if(mapPoint.getPointOwner() != null && Objects.equals(mapPoint.getPointOwner().getRole().getRoleName(), role.getRoleName())) {
                for(MapPoint volunteer_point : volunteers_points) {
                    double dist = point.checkDistance(volunteer_point);
                    if(dist < 10 && point.getType() == PointType.VICTIM) {
                        notificationService.notify("New victim within " + dist + "km needs your help! Please check: " +
                                point.getCoordinates().lat + " " + point.getCoordinates().lng, NotificationType.INFORMATION, volunteer_point.getPointOwner());
                    }
                }
            }
        }
        return point;
    }

    @Override
    public MapPoint getPoint(long id) {
        return mapPointRepository.findById(id).get();
    }

    @Override
    public List<MapPoint> getPoints() {
        return mapPointRepository.findAll();
    }

    @Override
    public List<MapPoint> getPointsByType(PointType pointType) {
        return mapPointRepository.findByType(pointType);
    }

    @Override
    public void removePoint(long id) {
        Optional<MapPoint> point_opt = mapPointRepository.findById(id);
        if(point_opt.isEmpty()) {
            throw new RuntimeException("No point found with id " + id);
        }
        if(point_opt.get().getType() == PointType.WAREHOUSE) {
            Optional<Warehouse> warehouse_opt = warehouseRepository.findByMapPoint(point_opt.get());
            if(warehouse_opt.isEmpty()) {
                throw new RuntimeException("No warehouse foundwith id " + id);
            }
            warehouseRepository.delete(warehouse_opt.get());
        } else {
            MapPoint point = point_opt.get();
            Role role = new Role("VOLUNTEER");
            List<MapPoint> volunteers_points = getPointsByType(PointType.VOLUNTEER);
            for (MapPoint mapPoint : volunteers_points) {
                if (mapPoint.getPointOwner() != null && Objects.equals(mapPoint.getPointOwner().getRole().getRoleName(), role.getRoleName())) {
                    for (MapPoint volunteer_point : volunteers_points) {
                        double dist = point.checkDistance(volunteer_point);
                        if (dist < 10 && point.getType() == PointType.VICTIM) {
                            notificationService.notify("Victim at: " +
                                    point.getCoordinates().lat + " " + point.getCoordinates().lng + " no longer needs help!", NotificationType.INFORMATION, volunteer_point.getPointOwner());
                        }
                    }
                }
            }
        }
        mapPointRepository.delete(mapPointRepository.findById(id).get());
    }

    @Override
    public void changeStatus(long id, boolean status) {
        Optional<MapPoint> point_opt = mapPointRepository.findById(id);
        if(point_opt.isPresent()) {
            MapPoint point = point_opt.get();
            Role role = new Role("VOLUNTEER");
            List<MapPoint> volunteers_points = getPointsByType(PointType.VOLUNTEER);
            for (MapPoint mapPoint : volunteers_points) {
                if(mapPoint.getPointOwner() != null && Objects.equals(mapPoint.getPointOwner().getRole().getRoleName(), role.getRoleName())) {
                    for(MapPoint volunteer_point : volunteers_points) {
                        double dist = point.checkDistance(volunteer_point);
                        if(dist < 10 && point.getType() == PointType.VICTIM && status) {
                            notificationService.notify("New victim within " + dist + "km needs your help! Please check: " +
                                    point.getCoordinates().lat + " " + point.getCoordinates().lng, NotificationType.INFORMATION, volunteer_point.getPointOwner());
                        } else if(dist < 10 && point.getType() == PointType.VICTIM) {
                            notificationService.notify("Victim at: " +
                                    point.getCoordinates().lat + " " + point.getCoordinates().lng + " no longer needs help!", NotificationType.INFORMATION, volunteer_point.getPointOwner());
                        }
                    }
                }
            }
        }
        mapPointRepository.updateActiveByPointID(id, status);
    }

    @Override
    public List<MapPoint> findByActive(boolean active) {
        return mapPointRepository.findByActive(active);
    }

    @Override
    public MapPoint updateMapPoint(Long id, MapPoint mapPoint) {
        return mapPointRepository.findById(id).map(existingMapPoint -> {
            existingMapPoint.setTitle(mapPoint.getTitle());
            existingMapPoint.setDescription(mapPoint.getDescription());


            return mapPointRepository.save(existingMapPoint);
        }).orElse(null);
    }
}
