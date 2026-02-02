package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessStatic {}
sealed class SuperGeneric<T> permits Target, Source {protected T baseValue;
public SuperGeneric(T value) {this.baseValue = value;}}
non-sealed class Target<T> extends SuperGeneric<T> {public Target(T value) {super(value);}
class Inner {class InnerRec {T recData;
InnerRec(T data) {this.recData = data;}
T getRecData() {return recData;}}}}
non-sealed class Source<T, U> extends SuperGeneric {
static class FirstStaticNested {
static class Inner {
@ProcessStatic
public static final <V> Target<V>.Inner.InnerRec process(Target<V> target, V data) {
// Expression statement
Target<V>.Inner inner = target.new Inner();
Target<V>.Inner.InnerRec rec = inner.new InnerRec(data);
// Variable call (access target field)rec.recData = target.baseValue;
// CastExpression (1 instance)V castedData = (V) target.baseValue;rec.recData = castedData;
// Requires try-catchtry {if (rec.getRecData() == null) {throw new NullPointerException("InnerRec data is null");}} catch (NullPointerException e) {// Handle exception without throwing new onee.printStackTrace();}
return rec;}}}
static class SecondStaticNested {// Additional static nested class as required}
public Source(U value) {super(value);}}