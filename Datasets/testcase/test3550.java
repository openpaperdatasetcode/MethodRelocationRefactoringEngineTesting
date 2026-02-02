package test;
import java.sql.SQLException;
record SourceRecord(int id) {static class StaticNested {}
class MemberInner {}
protected static TargetRecord<String> moveMethod(TargetRecord.TargetInner.TargetInnerRec param) {loop: for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue loop;}param.doAction();}try {param.handleSQL();} catch (SQLException e) {e.printStackTrace();}return new TargetRecord<>("data");}}
abstract record TargetRecord<T>(T data) {class TargetInner {class TargetInnerRec {void doAction() {}void handleSQL() throws SQLException {}}}
void someMethod() {class LocalInner {}}}