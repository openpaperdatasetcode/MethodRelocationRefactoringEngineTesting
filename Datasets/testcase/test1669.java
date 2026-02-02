package test;
import java.util.List;import java.util.ArrayList;
public abstract class SourceClass {private TargetClass targetField;
static class StaticNested {class InnerRec {final List<String> method() {class LocalType {}variableCall();
Runnable r = () -> recursiveMethod(0);return new ArrayList<>();}
public int recursiveMethod(int n) {if (n < 0) {throw new IllegalArgumentException();}return n == 0 ? 1 : n * recursiveMethod(n - 1);}
private void variableCall() {}}}
{new Runnable() {};}}
/**
Javadoc for TargetClass*/abstract class TargetClass {{new Runnable() {};}
class InnerRec {}}