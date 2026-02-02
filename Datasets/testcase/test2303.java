package same.pkg;
import java.io.IOException;import java.util.List;
abstract class Source<T> {T outerField;
class SourceInner {protected Object instanceMethod(Target<String> targetParam) throws IOException {// Constructor invocationTarget.InnerRec innerRec = targetParam.new InnerRec();
// Enhanced for statementfor (String s : targetParam.items) {System.out.println(s);}
// Variable callObject varCall = targetParam.process();
// Requires try-catchtry {innerRec.write();} catch (IOException e) {throw e;}
// Uses outer thisT val = Source.this.outerField;
return varCall;}}
// Anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {new SourceInner().instanceMethod(new Target<>());}};}
private class Target {
List items;
record InnerRec() {void write() throws IOException {// Sample I/O operation requiring try-catch}}
Object process() {return new Object();}}