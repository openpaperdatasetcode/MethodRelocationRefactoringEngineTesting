package test;
import java.util.List;import java.util.ArrayList;
@interface Source {Target targetField;
static class Nested {static class NestedInner {private int field;
final List<String> varargsMethod(String... args) {labeled: {this.field = 1;break labeled;}
List<String> list = new ArrayList<>();for (String s : args) {list.add(s);}
if (args.length > 0) {String str = args[0];}
class LocalType {}LocalType lt = new LocalType();
Nested nested = new Nested();int val = nested.protectedField;
return list;}}}
void methodWithLocalInner() {class LocalInner {}}}
public @interface Target {default void methodWithAnonymous() {Runnable r = new Runnable() {public void run() {}};}}
class Nested extends Source.Nested {protected int protectedField;}