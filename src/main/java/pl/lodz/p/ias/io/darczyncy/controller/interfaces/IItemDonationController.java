package pl.lodz.p.ias.io.darczyncy.controller.interfaces;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.ias.io.darczyncy.dto.create.ItemDonationCreateDTO;
import pl.lodz.p.ias.io.darczyncy.dto.update.ItemDonationUpdateDTO;
import pl.lodz.p.ias.io.darczyncy.utils.DonationConstants;

@RequestMapping(DonationConstants.APPLICATION_CONTEXT + "/donations/item")
public interface IItemDonationController {

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createItemDonation(@Valid @RequestBody ItemDonationCreateDTO itemDonationCreateDTO);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> findItemDonationById(@PathVariable long id);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> findAllItemDonations();

    @GetMapping(value = "/{id}/confirmation", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<?> getConfirmationDonationById(@PathVariable("id") long id);

    @GetMapping(value = "/donor/{id}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> findAllItemDonationsByDonorId(@PathVariable("id") long donorId);

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateItemDonation(@PathVariable("id") long id, @RequestBody ItemDonationUpdateDTO itemDonationUpdateDTO);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteItemDonationById(@PathVariable("id") long id);
}
