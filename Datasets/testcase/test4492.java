package test;
public class SourceClass {int value = 5;TargetClass target = new TargetClass();
class MemberInner {void useMethod() {int result = sourceMethod(target);}}
void sourceMethod(TargetClass param) {int var = 10;List rawList = new ArrayList();labeled: {switch (var) {case 10:Runnable r = () -> System.out.println(this.value);r.run();param.callMethod(rawList);break labeled;default:break;}}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {sourceMethod(target);}};}}
class TargetClass {private List<String> callMethod(List args) {return new ArrayList<>();}
class TargetInnerRec {void invoke() {new Runnable() {public void run() {TargetClass.this.callMethod(new ArrayList());}};}}}