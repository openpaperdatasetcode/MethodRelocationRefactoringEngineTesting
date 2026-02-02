package test;
interface SourceInterface {class MemberInner {private int sourceField;
private MemberInner(TargetInterface target) {super();if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
this.sourceField = TargetInterface.StaticNested.getFieldValue(target);int targetField = TargetInterface.StaticNested.FIELD;
class LocalInner {void processSwitch() {switch (sourceField) {case 1:System.out.println("Matched super.field: " + targetField);break;default:System.out.println("Default case");}}}
new LocalInner().processSwitch();}
private MemberInner(TargetInterface target, int extraParam) {this(target);this.sourceField += extraParam;}}
Runnable ANONYMOUS = new Runnable() {@Overridepublic void run() {MemberInner inner = new MemberInner(TargetInterface.DEFAULT);}};}
public interface TargetInterface {TargetInterface DEFAULT = new TargetInterface() {};
static class StaticNested {public static final int FIELD = 1;
public static int getFieldValue(TargetInterface target) {return FIELD;}}}