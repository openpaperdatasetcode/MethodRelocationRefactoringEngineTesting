package test.same;
import java.io.IOException;
class SourceClass {class MemberInner {record InnerRec(TargetClass target) {private abstract void abstractMethod() throws IOException;}}
Runnable anon1 = new Runnable() {public void run() {}};
Runnable anon2 = new Runnable() {public void run() {}};}
protected class TargetClass {void createLocal() {class LocalInner {Object field;}LocalInner local = new LocalInner();Object var = local.field;}}