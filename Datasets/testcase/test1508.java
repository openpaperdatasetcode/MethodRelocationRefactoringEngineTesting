package test;
import java.io.IOException;import java.util.ArrayList;import java.util.List;
record Target(String id) {class Inner {List<String> data = new ArrayList<>();
void add(String item) {data.add(item);}}}
record Source(String content) {class MemberInner {void process(Target.Inner targetInner) {targetInner.add(content);}}
private void handle(Target target) {// Constructor invocationTarget.Inner targetInner = target.new Inner();MemberInner sourceInner = new MemberInner();
// SynchronizedStatement with 1 target object field accesssynchronized (this) {String item = targetInner.data.get(0); // Access target inner fieldsourceInner.process(targetInner);}
try {if (targetInner.data.isEmpty()) {throw new IOException("Target inner data is empty");}} catch (IOException e) {// No new exception thrownSystem.err.println(e.getMessage());}
// Variable callsourceInner.process(targetInner);}}