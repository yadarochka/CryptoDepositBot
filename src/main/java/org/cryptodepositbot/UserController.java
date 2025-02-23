package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class UserController {

    public static void start(UserModel userModel) {
        if (UserService.isRegistered(userModel.getTelegramId())){
            String welcomeMessage = "Привет, " + userModel.getFirstName() + ", добро пожаловать! \uD83E\uDD19";
            UserService.createUser(userModel.getTelegramId(), userModel.getUsername());
            CryptoDepositBot.send(new SendMessage(userModel.getChatId(), welcomeMessage));
        }
        else {
            String welcomeMessage = "Привет, " + userModel.getFirstName() + ", добро пожаловать снова! \uD83D\uDE09";
            CryptoDepositBot.send(new SendMessage(userModel.getChatId(), welcomeMessage));
        }
    }

    public static void showMenu(UserModel userModel) {
        MenuKeyboard menuKeyboard = new MenuKeyboard(userModel.getChatId());

        boolean isAdmin = AdminService.isAdmin(userModel.getTelegramId());

        CryptoDepositBot.send(menuKeyboard.sendButtons(isAdmin));
    }
}
