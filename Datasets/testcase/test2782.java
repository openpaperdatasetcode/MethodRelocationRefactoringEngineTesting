package test;
private class SourceClass<T> {static class StaticNested {}
class SourceInner {private abstract Object methodToMove(TargetClass.TargetInner param) throws Exception {class LocalInner {public LocalInner(TargetClass.TargetInner t) {t.m1().m2().m3();}}LocalInner local = new LocalInner(param.m1().m2().m3());
T typeVar;param.toString();SourceClass.this.toString();}}}
class TargetClass {/**
Javadoc for TargetInner*/class TargetInner {public TargetInner m1() { return this; }public TargetInner m2() { return this; }public TargetInner m3() { return this; }
{new Runnable() {};}}}