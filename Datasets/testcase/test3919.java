import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {private int outerPrivateField;private String instanceField;public static int staticField;public TargetClass<T> targetField;
public SourceClass() {outerPrivateField = 0;instanceField = "source";staticField = 10;targetField = new TargetClass<>();}
@Deprecatedpublic List<String> constructor() {List<String> list = new ArrayList<>();for (int i = 0; i < 3; i++) {list.add(instanceField);list.add(String.valueOf(outerPrivateField));list.add(String.valueOf(staticField));list.add(targetField.nestedStaticClass.staticMethod());new NestedStaticClass().nonStaticMethod();InnerClass inner = new InnerClass();inner.innerMethod();}return list;}
static {staticRecursiveMethod(3);}
public static void staticRecursiveMethod(int n) {if (n <= 0) return;new TargetClass<>().nestedStaticClass.staticMethod();staticRecursiveMethod(n - 1);}
public class InnerClass {public void innerMethod() {System.out.println(outerPrivateField);System.out.println(instanceField);}}
public static class NestedStaticClass {public void nonStaticMethod() {System.out.println(SourceClass.staticField);}}}
public class TargetClass<T> {public static class NestedStaticClass {public static String staticMethod() {return "targetStatic";}}}