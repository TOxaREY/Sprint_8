import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@DisplayName("Тесты имени на карте")
@RunWith(Parameterized.class)
public class TestAccount {

    private Account account;
    private final boolean expected;

    public TestAccount(Account account, boolean expected) {
        this.account = account;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[] doesHaveMane() throws Exception {
        return new Object[][] {
                {new Account(""), false},//0
                {new Account("а"), false},//1
                {new Account("аб"), false},//2
                {new Account("а б"), true},//3
                {new Account("аб в"), true},//4
                {new Account("абвгд ежзл"), true},//10
                {new Account("абвгдежзл иклмнопр"), true},//18
                {new Account("абвгдеж зликлмнопрс"), true},//19
                {new Account("абвгдежзликл мнопрст"), false},//20
                {new Account("абвгдежзликл мнопрсту"), false},//21
                {new Account("абвгдежзликлмнопрстуабв гдежзликлмнопрсту"), false},//41
                {new Account("абвгд ежзли"), true},//пробел
                {new Account("абвгд  ежзли"), false},//2 пробела
                {new Account("абвгдежзли"), false},//нет пробела
                {new Account("абвгд          ежзли"), false},//10 пробелов
                {new Account(" абвгд ежзли"), false},//пробел в начале
                {new Account("абвгд ежзли "), false},//пробел в конце
                {new Account(" абвгд ежзли "), false}//пробел в начале и конце
        };
    }

    @Test
    @DisplayName("Позитивные и негативные тесты имени на карте")
    @Description("Применена параметризация")
    public void testAccountcheckNameToEmboss() {
        assertEquals(expected, account.checkNameToEmboss());
    }
}
