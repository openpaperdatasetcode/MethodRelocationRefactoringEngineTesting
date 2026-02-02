package test;
import java.io.IOException;
@interface SourceAnnotation {String value() default "";
class NestedHelper {private static String outerPrivate = "source_private_data";
private void processAnnotation(TargetAnnotation... targets) throws IOException {class LocalInner {void useOuterPrivate() {System.out.println(NestedHelper.outerPrivate);}}
new LocalInner().useOuterPrivate();
Runnable anonTask = new Runnable() {@Overridepublic void run() {for (TargetAnnotation target : targets) {System.out.println("Target value: " + target.value());}}};anonTask.run();
if (targets.length == 0) {throw new IOException("No TargetAnnotation parameters provided");}}}}
@interface TargetAnnotation {String value() default "";
class TargetHelper {public void createAnonymous() {Runnable anonTask = new Runnable() {@Overridepublic void run() {System.out.println("TargetAnnotation anonymous task");}};anonTask.run();}}}