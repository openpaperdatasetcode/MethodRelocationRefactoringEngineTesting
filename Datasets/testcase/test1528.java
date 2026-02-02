package test;
import java.sql.SQLException;
public sealed record SourceRecord<T>(T data) permits SubSourceRecord {public static class StaticNested {String info;}
public class MemberInner {final Object process(TargetRecord targetParam) throws SQLException {int i = 0;do {i++;targetParam.inner.update(i);} while (i < 3);
String var = "test";this.var = var;
StaticNested nested = new StaticNested();nested.info = super.data().toString();
return targetParam.value;}
private String var;}}
record SubSourceRecord(Integer id) extends SourceRecord<Integer> {SubSourceRecord {super(id);}}
record TargetRecord(String value) extends ParentRecord {public class MemberInner {void update(int num) {System.out.println(num);}}}
class ParentRecord {synchronized int callMethod() {SourceRecord<String> source = new SubSourceRecord(10);SourceRecord<String>.MemberInner inner = source.new MemberInner();TargetRecord target = new TargetRecord("target");
int result = 0;switch (source.data()) {case "case1" -> result = SourceRecord.StaticNested.class.hashCode();default -> {try {inner.process(target);result = 1;} catch (SQLException e) {result = -1;}}}return result;}}