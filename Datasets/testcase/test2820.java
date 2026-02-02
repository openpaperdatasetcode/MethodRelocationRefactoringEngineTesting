package test;
public enum SourceEnum {INSTANCE;
public class InnerSource {public TargetEnum.InnerTarget getTarget() {public int var = 2;Object[] arr = {TargetEnum.InnerTarget.accessor()};for (Object obj : arr) {}obj.toString();private Runnable r = () -> new Object();try {getClass().getMethod("getTarget").invoke(this);} catch (Exception e) {}var++;return TargetEnum.InnerTarget.INSTANCE;}}
static class StaticNestedSource {private int superField = TargetEnum.InnerTarget.superField;}}
enum TargetEnum {INSTANCE;
static class InnerTarget extends SourceEnum.StaticNestedSource {public static InnerTarget INSTANCE = new InnerTarget();public static Object accessor() {return null;}}}