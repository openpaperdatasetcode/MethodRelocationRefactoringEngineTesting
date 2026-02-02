package test;
import java.util.List;import java.util.ArrayList;
interface SuperInterface {List<String> process(TargetRecord target);}
public record SourceRecord(String data) implements SuperInterface {@Overridepublic final List<String> process(TargetRecord target) {class LocalInner {private void checkField() {if (target.field != null) {} // EmptyStatement with target_feature; // Explicit empty statement}}LocalInner local = new LocalInner();
Runnable anonymous = new Runnable() {public void run() {SourceRecord.this.data();}};
Label: {target.variableCall();if (target.field.isEmpty()) {break Label;}local.checkField();}
// Access instance fieldString value = target.field;
// Override violation (attempting to override final method)SuperInterface violating = new SuperInterface() {@Overridepublic List<String> process(TargetRecord t) {return new ArrayList<>();}};
return new ArrayList<>(List.of(value));}}
public record TargetRecord(String field) {void variableCall() {}}