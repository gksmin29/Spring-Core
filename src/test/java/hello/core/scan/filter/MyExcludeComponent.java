package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//컴포넌트 스캔에서 제외할 거
public @interface MyExcludeComponent {

}
