package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.Objects;

public class AdminController {
    public static void showAllUsers(String userId) {
        List<UserModel> userList = null;
        if (AdminService.isAdmin(userId)){
            userList = AdminService.showAllUsers();
        }

        if (userList == null){
            String message = "Не удалось получить список пользователей";
            CryptoDepositBot.send(new SendMessage(userId, message));
            return;
        }

        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder.append("```\n");
        messageBuilder.append("ID  | Имя        | Дата регистрации (МСК) | Роль \n");
        messageBuilder.append("----+------------+------------------------+------\n");

        for (int i = 0; i < userList.size(); i++) {
            String username = userList.get(i).getUsername();
            String createdAt = DateTimeUtil.getMoscowDateTime(userList.get(i).getCreatedAt());
            String role = userList.get(i).getRole();

            messageBuilder.append(String.format("%-3d | %-10s | %-22s | %-6s%n",
                    i, username, createdAt, role));
        }
        messageBuilder.append("\n```");

        SendMessage message = new SendMessage(userId, messageBuilder.toString());

        message.setParseMode("MarkdownV2");

        CryptoDepositBot.send(message);

    }

    public static void sendMessageToAllAdmins(Object message){
        List<String> adminsChatIds = AdminService.getAdminsChatIds();
        for (String chatId: adminsChatIds){
            SendMessage notify = (SendMessage) message;
            notify.setChatId(chatId);
            CryptoDepositBot.send(notify);
        }
    }
}
