package same;
import java.sql.SQLException;import java.util.ArrayList;
record Source(int val) {static class StaticNested {}Runnable anonInner = new Runnable() {public void run() {}};
public void instanceMethod(Target.TargetInner param) throws SQLException {protected void breakBlock() {for (int i = 0; i < 5; i++) {if (i == 2) {break;}System.out.println(super.val);}}breakBlock();}
protected ArrayList rawList = new ArrayList();variable call = param;param.accessInstanceMethod();}
public record Target(String data) implements Runnable {static class TargetInner {public void accessInstanceMethod() {}}
public void run() {}}