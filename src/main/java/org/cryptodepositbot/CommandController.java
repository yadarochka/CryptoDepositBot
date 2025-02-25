package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CommandController {

    public static void execute(String command, UserModel userModel) {
        System.out.println("Command: "+command+"\tUser: " + userModel.getUsername());
        switch (command) {
            case "/start":
                CommandController.start(userModel);
                break;
            case "/show_menu":
                CommandController.showMenu(userModel);
                break;
            default:
                break;
        }
    }


    private static void start(UserModel userModel) {
        if (UserService.isRegistered(userModel.getTelegramId())) {
            String welcomeMessage = "Привет, " + userModel.getFirstName() + ", добро пожаловать! \uD83E\uDD19";
            UserService.createUser(userModel.getTelegramId(), userModel.getUsername());
            CryptoDepositBot.send(new SendMessage(userModel.getChatId(), welcomeMessage));
        } else {
            String welcomeMessage = "Привет, " + userModel.getFirstName() + ", добро пожаловать снова! \uD83D\uDE09";
            CryptoDepositBot.send(new SendMessage(userModel.getChatId(), welcomeMessage));
        }
    }

    private static void showMenu(UserModel userModel) {
        boolean isAdmin = AdminService.isAdmin(userModel.getTelegramId());
        Menu.createInlineKeyboard(userModel.getChatId(), MenuList.showMenu(isAdmin));
    }
}
