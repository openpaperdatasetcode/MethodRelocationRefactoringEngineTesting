package samepkg;
import java.util.List;import java.util.ArrayList;
class ParentSourceClass {public abstract List<String> overridingMethod(AbstractTargetClass target);}
private class SourceClass extends ParentSourceClass {private int outerPrivateField1 = 1;private int outerPrivateField2 = 2;
@Overridepublic List<String> overridingMethod(AbstractTargetClass targetParam) {private int var1 = this.outerPrivateField1;private int var2 = this.outerPrivateField2;
super.overridingMethod(targetParam);AbstractTargetClass varCall = targetParam;int outerPrivate = SourceClass.this.outerPrivateField1;
try {varCall.toString();} catch (Exception e) {}
return new ArrayList<>();}
{Runnable r1 = new Runnable() {@Overridepublic void run() {}};Runnable r2 = new Runnable() {@Overridepublic void run() {}};}}
abstract class AbstractTargetClass {}