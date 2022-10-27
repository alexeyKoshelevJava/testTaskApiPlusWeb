package ru.koshelev.apitests;

import helpers.Assertions;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pojo.SingleUser;

import static helpers.RequestUtils.sendGet;
import static helpers.Specification.requestSpec;

public class RegressTest {
    @Feature("проверка имени")
    @DisplayName("Проверка имени юзера ")
    @ParameterizedTest(name = "{displayName}")
    @Owner("@Koshelev")
    @CsvSource("Janet")
    void singleUserName(String userName) {
        SingleUser user = sendGet(requestSpec());
        Assertions.assertEquals(userName, user.getData().getFirst_name(), "имя пользователя не соответствуте заданному: " + userName);

    }
}
