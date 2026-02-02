package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
public class SourceClass {protected String outerProtected = "protected_data";
public class MemberInner {public TargetClass.Inner.Rec getRec(int id) {return new TargetClass().new Inner().new Rec(id);}}
@ProcessAnnotationpublic static TargetClass process(int baseId) {// Local inner classclass TargetCreator {TargetClass create() {TargetClass target = new TargetClass();TargetClass.Inner.Rec rec = target.new Inner().new Rec(baseId);// Access outer protected fieldrec.data = outerProtected;return target;}}TargetClass target = new TargetCreator().create();
// Variable call - access target inner rec's fieldSystem.out.println("Rec ID: " + target.inner.rec.id);
// Override violation (attempt to override final method)target.inner = new TargetClass.Inner() {@Overridepublic void finalMethod() {// Compile error if finalMethod() is declared final}};
return target;}}
class TargetClass {public Inner inner = new Inner();
public class Inner {public Rec rec;
public Inner() {// Anonymous inner class in targetRunnable initializer = new Runnable() {@Overridepublic void run() {rec = new Rec(0);}};initializer.run();}
public final void finalMethod() {}
public class Rec {public int id;public String data;
public Rec(int id) {this.id = id;}}}}