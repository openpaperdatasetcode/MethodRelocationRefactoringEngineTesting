package test;
import java.util.function.Function;
@RefactorAnnotationpublic class SourceClass extends ParentClass {public static class StaticNested {}
@RefactorAnnotationprivate Object moveMethod (PublicTarget target) {super (); class LocalInner {}new LocalInner ();
PublicTarget.LocalInnerHelper helper = target.new LocalInnerHelper ();Object processed = helper.process (target.data);System.out.println (processed); return processed;}
@Overridepublic final PublicTarget.LocalInnerHelper callMethod () {Function<String, PublicTarget.LocalInnerHelper> creator = str ->PublicTarget.LocalInnerHelper.create (new PublicTarget (str));
PublicTarget.LocalInnerHelper helper1 = creator.apply ("test1");PublicTarget.LocalInnerHelper helper2 = PublicTarget.LocalInnerHelper.create (new PublicTarget ("test2"), 100);
return helper1;}}
abstract class ParentClass {protected ParentClass() {}public abstract PublicTarget.LocalInnerHelper callMethod();}
public class PublicTarget {String data;
public PublicTarget(String data) {this.data = data;}
class LocalInnerHelper {public Object process(String input) {class TargetLocalInner {}new TargetLocalInner();return input + data;}
OuterClass.InnerClass.methodName ()public static LocalInnerHelper create (PublicTarget outer) {return outer.new LocalInnerHelper ();}
public static LocalInnerHelper create(PublicTarget outer, int param) {LocalInnerHelper helper = outer.new LocalInnerHelper();helper.process(outer.data + param);return helper;}}
{class TargetLocalInner {}new TargetLocalInner();}}
@interface RefactorAnnotation {}