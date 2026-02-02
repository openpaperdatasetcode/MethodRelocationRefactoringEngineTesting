package test;
interface MyInterface {}
protected class SourceClass implements MyInterface {static class StaticNestedOne {}static class StaticNestedTwo {}
class Inner {class InnerRec {@SuppressWarnings("rawtypes")void handle(TargetClass target) throws IllegalArgumentException {TargetClass.Inner inner = target.new Inner();List rawList = new ArrayList();rawList.add(inner.field);
new Runnable() {@Overridepublic void run() {System.out.println(inner.field);}};
if (inner.field < 0) {throw new IllegalArgumentException();}}}}}
protected class TargetClass<T> {class Inner {int field;}
{new Runnable() {@Overridepublic void run() {}};}}