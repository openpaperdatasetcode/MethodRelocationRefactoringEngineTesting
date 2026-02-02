package same.pkg;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
private record Source(String data) {static class StaticNested {}
@CustomAnnotationclass SourceInner {/**
Overloaded method with Target parameter
@param targetParam the target record instance
@return Object result*/protected Object overloadedMethod(Target targetParam) throws IOException {// Constructor invocationTarget.LocalCreator creator = targetParam.new LocalCreator();
// Do statementint i = 0;do {i++;} while (i < targetParam.value().length());
// Super constructor invocation (in anonymous inner class)Runnable anon = new Runnable() {{super();}@Overridepublic void run() {}};
// IOExceptionif (targetParam.value().isEmpty()) {throw new IOException("Empty value");}
// Variable callObject varCall = creator.create(targetParam);
// Override violation (assuming LocalCreator has restricted method)creator.overrideRestricted();
return varCall;}
/**
Overloaded method with String parameter
@param str input string
@return Object result
*/
protected Object overloadedMethod(String str) {
return str;
}
}
// Anonymous inner classRunnable anonAction = new Runnable() {@Overridepublic void run() {new SourceInner().overloadedMethod(new Target("test"));}};}
protected record Target(String value) {Object createLocal() {// Local inner classclass LocalCreator {Object create(Target target) {return target.value();}
private void overrideRestricted() {}}return new LocalCreator();}
LocalCreator new LocalCreator() {return (LocalCreator) createLocal();}}