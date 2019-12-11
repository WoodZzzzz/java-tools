import com.wood.tool.reflect.Letter;
import com.wood.tool.reflect.LetterAccounts;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author:thinkpad
 * @date:2019/6/15
 * @desc: TODO
 **/
public class TestReflect {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Object o = new Object();
        List list = (List)o;
        LetterAccounts accounts = new LetterAccounts();
        accounts.setAccount("zhouwenjie");
        Letter letter = new Letter();
        letter.setList(Arrays.asList(accounts));
        letter.setName("函件1");
        Field field = letter.getClass().getDeclaredField("list");
        field.setAccessible(true);
        Type type = field.getGenericType();

        Field field2 = letter.getClass().getDeclaredField("name");
        field.setAccessible(true);
        Type type2 = field2.getGenericType();
        if (type instanceof ParameterizedType) {
            // 判断参数类型
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type[] types = parameterizedType.getActualTypeArguments();
        }
        Object value = field.get(letter);

        System.out.println(value);
    }
}
