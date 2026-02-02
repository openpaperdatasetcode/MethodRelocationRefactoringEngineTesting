class SourceClass {private String outerPrivate = "source_private_data";
static class StaticNested {}
{Runnable r = new Runnable() {public void run() {new SourceClass().process(new TargetClass());}};}
private TargetClass process(TargetClass target) {if (target == null) {throw new IllegalArgumentException("Target class parameter cannot be null");}
// Expression statementString processedData = outerPrivate + "_processed";
// Do statementint count = 0;do {// Variable call + access target inner classtarget.new MemberInner().setValue(processedData + "_" + count);// Access outer private field in looptarget.new MemberInner().appendData(outerPrivate);count++;} while (count < 3);
return target;}}
private class TargetClass {class MemberInner {private String innerValue;
void setValue(String val) {this.innerValue = val;}
void appendData(String data) {this.innerValue += "_" + data;}
String getInnerValue() {return innerValue;}}}