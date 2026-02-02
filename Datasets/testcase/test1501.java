package test;
import java.util.ArrayList;import java.util.List;import java.util.regex.Pattern;
sealed class Source permits Source.SubSource {private String outerPrivate = "private_data";
class FirstInner {List<String> process(Target target, String... args) {List<String> result = new ArrayList<>();
// Access outer private field using outer thisresult.add(Source.this.outerPrivate);
// 3 Pattern instancesPattern pattern1 = Pattern.compile("\d+");Pattern pattern2 = Pattern.compile("[A-Z]+");Pattern pattern3 = Pattern.compile("\s+");
// Access target fieldif (target.data == null) {throw new NullPointerException("Target data is null");}result.add(target.data);
// Access instance methodtarget.action.run();
// Array initialization with source overriding method callObject[] processed = {new SubSource().handle(args),pattern1.matcher(args[0]).matches()};
return result;}}
class SecondInner {protected List<String> handle(String[] items) {return List.of(items);}}
static final class SubSource extends Source {@Overrideprotected List<String> handle(String[] items) {List<String> processed = new ArrayList<>();for (String item : items) {processed.add(item.toUpperCase());}return processed;}}}
class Target {String data;Runnable action;
{// Anonymous inner classaction = new Runnable() {@Overridepublic void run() {data = "processed_by_anon";}};}}
