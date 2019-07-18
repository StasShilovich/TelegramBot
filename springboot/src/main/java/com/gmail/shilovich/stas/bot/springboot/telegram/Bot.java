package com.gmail.shilovich.stas.bot.springboot.telegram;

import com.gmail.shilovich.stas.bot.service.BotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import static com.gmail.shilovich.stas.bot.springboot.constant.TelegramStatic.BOT_TOKEN;
import static com.gmail.shilovich.stas.bot.springboot.constant.TelegramStatic.BOT_USERNAME;

@Component
public class Bot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    private final BotService botService;
    private static TelegramBotsApi telegramBotsApi;

    public Bot(BotService botService) {
        this.botService = botService;
        if (telegramBotsApi == null) {
            telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(new Bot(botService));
            } catch (TelegramApiRequestException e) {
                logger.info("Telegram registration problem", e);
            }
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        String userMessage = message.getText().trim().toLowerCase();
        boolean cityExists = botService.isCityExists(userMessage);
        if (cityExists) {
            String cityInfo = botService.getCityInfo(userMessage);
            sendMessage.setText(cityInfo);
        } else if (userMessage.equals("/start")) {
            sendMessage.setText("Hello! It is Travel Bot! Write the city to find out information about it ");
        } else {
            sendMessage.setText("This city is not found");
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.info("Message sending exception ", e);
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
