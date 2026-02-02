package test.same;
import java.lang.reflect.Field;
public enum SourceEnum permits ExtendedSourceEnum {INSTANCE;
static class StaticNested {}
public void normalMethod() {TargetEnum target = TargetEnum.VALUE;TargetEnum.InnerRec rec = target.new InnerRec();Object var = rec.targetField;
class LocalInner {TargetEnum.InnerRec getRec() {return rec;}}LocalInner local = new LocalInner();
try {Field field = TargetEnum.InnerRec.class.getDeclaredField("targetField");field.setAccessible(true);var = field.get(rec);} catch (Exception e) {}}}
enum ExtendedSourceEnum extends SourceEnum {}
/**
Javadoc for TargetEnum
Contains anonymous inner class and record inner class*/private enum TargetEnum {VALUE;
record InnerRec() {Object targetField;}
Runnable anon = new Runnable() {public void run() {}};}