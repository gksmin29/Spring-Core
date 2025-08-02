package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//컴포넌트 스캔에 포함할 거
public @interface MyIncludeComponent {
}
