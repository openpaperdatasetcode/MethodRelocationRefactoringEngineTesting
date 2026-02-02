package test;
class SourceClass {/**
Javadoc for moveMethod: Processes TargetClass parameter and performs related operations
@param target Parameter of TargetClass type
*/
protected void moveMethod(TargetClass target) {
TargetClass newTarget = new TargetClass();
int val = target.getValue();
this.val = val;
target.executeAction();
newTarget.executeAction();
}
private int val;}
final class TargetClass implements Runnable {private int value = 10;
public int getValue() {return value;}
public void executeAction() {}
{Runnable anon = new Runnable() {@Overridepublic void run() {executeAction();}};}
@Overridepublic void run() {}}