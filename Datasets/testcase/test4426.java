package source;
private class SourceClass {class MemberInner {}static class StaticNested {}
@SuppressWarnings("unchecked")SourceClass(target.TargetClass.Nested param) {class LocalType {}LocalType local = new LocalType();int count = 0;while (count < 5) {try {if (param == null) {throw new NullPointerException();}int var = param.nestedField;count++;} catch (NullPointerException e) {e.printStackTrace();break;}}}}
package target;
public class TargetClass {static class Nested {int nestedField;}}