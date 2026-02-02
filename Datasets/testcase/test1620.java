package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
sealed enum SourceEnum permits ExtendedSourceEnum {INSTANCE;
public class MemberInner {public TargetEnum.Inner.Rec createRec(String data) {return TargetEnum.VALUE1.new Inner().new Rec(data);}}
protected void setValue(TargetEnum.Inner.Rec targetRec) throws SQLException {// Local inner classclass RecUpdater {void update(TargetEnum.Inner.Rec rec, String value) {rec.data = value;}}RecUpdater updater = new RecUpdater();
// Constructor invocationMemberInner inner = new MemberInner();List<TargetEnum.Inner.Rec> recs = new ArrayList<>();recs.add(inner.createRec("initial"));
// For statementfor (int i = 0; i < recs.size(); i++) {// Variable call - access target inner rec's fieldupdater.update(recs.get(i), targetRec.data + "_copy" + i);}
// Check for SQL exception conditionif (targetRec.data == null) {throw new SQLException("Target record data cannot be null");}
// Update target inner rec's fieldtargetRec.data = "updated_value";}}
non-sealed enum ExtendedSourceEnum implements SourceEnum {}
public enum TargetEnum {VALUE1, VALUE2;
public class Inner {public class Rec {public String data;
public Rec(String data) {this.data = data;}}}}