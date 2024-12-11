package pl.lodz.p.ias.io.komunikacja.mapper;

import pl.lodz.p.ias.io.komunikacja.dto.ChatRoomDTO;
import pl.lodz.p.ias.io.komunikacja.model.ChatRoom;
import pl.lodz.p.ias.io.uwierzytelnianie.model.Account;

import java.util.List;
import java.util.stream.Collectors;

public class ChatRoomMapper {
    public static ChatRoomDTO toDTO(ChatRoom chatRoom) {
        ChatRoomDTO dto = new ChatRoomDTO();
        dto.setId(chatRoom.getId());
        dto.setUsers(chatRoom.getUsers().stream()
                .map(Account::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public static ChatRoom toEntity(List<Account> users) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setUsers(users);
        return chatRoom;
    }

    public static List<ChatRoomDTO> toDTOList(List<ChatRoom> chatRooms) {
        return chatRooms.stream()
                .map(ChatRoomMapper::toDTO)
                .collect(Collectors.toList());
    }
}
