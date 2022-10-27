package ru.koshelev.webtests;

import helpers.Steps;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class WebTests extends BaseTest {
    @Feature("Проверка почты")
    @DisplayName("№1. вход в yandex почту")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource("awesome.testfirstname,123456Test!")
    void logInToMail(String login, String pass) {
        Steps.goToPage("https://mail.yandex.ru/");
        Steps.goToYandexMailBox(login, pass);
    }

    @Feature("результаты поиска")
    @DisplayName("проверка результатов поиска Google")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource("купить кофемашину bork c804,//www.mvideo.ru,10")
    void searchItemIntoGoogle(String itemName,String urlName,int count) {
        Steps.goToPage("https://www.google.ru/");
        Steps.googleSearch(itemName);
        Steps.checkUrlPresentIntoSearchAndCountItemMoreThan(urlName,count);

    }
}
