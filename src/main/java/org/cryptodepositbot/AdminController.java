package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public class AdminController {
    public static void showAllUsers(Long telegramId, String chatId) {
        List<UserModel> userList = null;
        if (AdminService.isAdmin(telegramId)){
            userList = AdminService.showAllUsers();
        }

        if (userList == null){
            String message = "Не удалось получить список пользователей";
            CryptoDepositBot.send(new SendMessage(chatId, message));
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

        SendMessage message = new SendMessage(chatId, messageBuilder.toString());

        message.setParseMode("MarkdownV2");

        CryptoDepositBot.send(message);

    }
}
