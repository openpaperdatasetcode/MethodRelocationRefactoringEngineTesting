package test;
import java.io.IOException;
@interface SourceAnnotation {String value() default "source-default";
// Constructor-like method (interface can't have real constructors, use static factory as alternative)static SourceAnnotation create(TargetAnnotation.TargetInner targetInner) throws IOException {class LocalInner {void validateTarget(TargetAnnotation.TargetInner inner) throws IOException {if (inner.getDetail() == null) {throw new IOException("Target inner detail is null");}}}
LocalInner validator = new LocalInner();int i = 0;do {validator.validateTarget(targetInner);String targetData = targetInner.getDetail();i++;} while (i < 2);
return new SourceAnnotation() {};}
default void useAnonymous() throws IOException {TargetAnnotation.TargetInner targetInner = new TargetAnnotation.TargetInner() {@Overridepublic String getDetail() {return "anon-target-detail";}};SourceAnnotation.create(targetInner);}}
abstract @interface TargetAnnotation {String name() default "target-default";
abstract class TargetInner {public abstract String getDetail();
public TargetInner() {Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("TargetInner anonymous run");}};anon.run();}}}