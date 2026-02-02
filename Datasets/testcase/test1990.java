package test;
import java.sql.SQLException;import java.util.function.Consumer;
@FeatureAnnotationpublic enum SourceEnum {FIRST, SECOND;
// Static nested classpublic static class SourceStatic {public static final String STATIC_DATA = "source_static";}
// Member inner classpublic class SourceInner {private String innerField;
public SourceInner(String data) {this.innerField = data;}
public String getInnerField() {return innerField;}}
// Strictfp instance methodstrictfp Object instanceMethod() {// Constructor invocation for target's inner classPrivateTarget.Inner targetInner = PrivateTarget.VALUE1.new Inner("init_data");
// Variable callObject result = targetInner.getInfo();
// Depends on static fieldif (SourceStatic.STATIC_DATA.equals(targetInner.getPrefix())) {result = result + "_matched";}
// Handle exceptionstry {// SQLExceptionif (targetInner.getInfo().isEmpty()) {throw new SQLException("Empty data");}// NullPointerExceptionString nullCheck = targetInner.getInfo().toUpperCase();} catch (SQLException | NullPointerException e) {// No new exceptionresult = "error_handled";}
// Functional interface with private abstract sub_class methodConsumer<PrivateTarget.Inner> processor = data -> new AbstractHandler().handle(data);processor.accept(targetInner);
// Lambda expression with target instance method callRunnable targetAction = () -> new PrivateTarget.Inner("lambda") {@Overridepublic String getInfo() {return super.getInfo() + "_lambda";}}.log();targetAction.run();
return result;}
// Private abstract class for sub_class methodprivate abstract class AbstractHandler {abstract void handle(PrivateTarget.Inner data);}
// Subclass implementing abstract methodprivate class ConcreteHandler extends AbstractHandler {@Overridevoid handle(PrivateTarget.Inner data) {data.setInfo(data.getInfo() + "_handled");}}}
@interface FeatureAnnotation {}
// Private target enumprivate enum PrivateTarget {VALUE1, VALUE2;
// Member inner classpublic class Inner {private String info;private final String prefix = SourceEnum.SourceStatic.STATIC_DATA;
public Inner(String info) {this.info = info;}
public String getInfo() {return info;}
public void setInfo(String info) {this.info = info;}
public String getPrefix() {return prefix;}
// Public instance method for call_methodpublic void log() {System.out.println("Logged: " + info);}}}