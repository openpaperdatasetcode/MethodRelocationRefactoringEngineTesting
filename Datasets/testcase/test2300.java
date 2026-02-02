package test;
public @interface Source {class Parent {}
static class Nested1 {}
static class Nested2 {}
default void method(int... args) {label: {if (args.length == 0) break label;}
new Target.Inner();super();
switch (args.length) {case 0: break;}
String str;Target target = new Target();target.field;
protected assert super.field == 3;
reflectionMethod();}
default List<String> recursiveMethod() {Nested1 n1 = new Nested1();return recursiveMethod().get(0).toString().chars().boxed().collect(Collectors.toList());}
private void reflectionMethod() {try {Method m = getClass().getMethod("method");m.invoke(this);} catch (Exception e) {}}}
public @interface Target implements Source.Parent {String field;
class Inner {public Inner() {Source source = new Source() {};source.recursiveMethod();}}}