package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
interface TargetInterface {static class TargetStaticNested {int nestedField = 5;}}
interface SourceInterface {static class SourceStaticNested {TargetInterface.TargetStaticNested targetNested = new TargetInterface.TargetStaticNested();}
default Object sourceMethod() {class LocalType {LocalType() {super();}TargetInterface.TargetStaticNested field = new SourceStaticNested().targetNested;}LocalType local = new LocalType();
List rawList = new ArrayList();rawList.add(local.field);
if (local.field.nestedField < 0) {throw new IllegalArgumentException("Invalid field value");}
Runnable r = new Runnable() {@Overridepublic void run() {try {if (SourceInterface.this.toString() == null) {throw new SQLException("Null string");}} catch (SQLException e) {e.printStackTrace();}}};r.run();
return local.field.nestedField;}}