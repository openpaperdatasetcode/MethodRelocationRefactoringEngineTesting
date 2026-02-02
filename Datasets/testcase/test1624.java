package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CallInfo {String value();}
abstract class SourceClass {public class MemberInner {public class InnerRec {@CallInfo(InnerClass.staticCall().process().getValue())public TargetClass.Inner process(TargetClass.Inner targetInner) {// Local inner classclass TargetProcessor {TargetClass.Inner handle(TargetClass.Inner inner) {inner.data = "processed";return inner;}}TargetProcessor processor = new TargetProcessor();
// Type declaration statementTargetClass.Inner newInner = targetInner.new Inner();
// Variable call - access target inner's fieldnewInner.data = targetInner.data;
return processor.handle(newInner);}}}
public static class InnerClass {public static Processor staticCall() {return new Processor();}
public static class Processor {public Handler process() {return new Handler();}
public static class Handler {public String getValue() {return "called";}}}}}
public class TargetClass {public class Inner {public String data;
public Inner() {// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {data = "initial";}};init.run();}}}