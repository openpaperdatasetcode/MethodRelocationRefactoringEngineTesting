package test;
import java.util.Arrays;
private enum SourceEnum {INSTANCE;
protected int outerProtected = 5;static class StaticNestedOne {}static class StaticNestedTwo {}
public TargetEnum process(TargetEnum... targets) {TargetEnum result = TargetEnum.VALUE;new TargetEnum.StaticNested();
if (TargetEnum.STATIC_FIELD == 3) ;
for (TargetEnum target : targets) {if (target.getField() > outerProtected) {result = target;break;}Object data = target.callSuperMethod(outerProtected);}
return result;}}
strictfp enum TargetEnum extends ParentClass {VALUE;
static int STATIC_FIELD = 3;static class StaticNested {}
{new Runnable() {@Overridepublic void run() {}};}
int getField() {return STATIC_FIELD;}
final Object callSuperMethod(int arg) {return super.toString() + arg;}}
class ParentClass {}