package test;
import java.io.IOException;
public class ParentClass {}
public class TargetClass extends ParentClass {public static class NestedStatic {public void instanceMethod() {}}}
class SourceClass {private TargetClass targetField;
public class MemberInner {}public static class StaticNested {}
@FunctionalInterfaceprivate interface LocalInterface {}
@Deprecatedvoid instanceMethod() throws IOException {label: {class LocalType {}LocalType local = new LocalType();
targetField;targetField.new NestedStatic().instanceMethod();
;break label;}}}