package test.same;
class SourceClass {protected Object varargsMethod(TargetClass... targets) {super.toString();TargetClass.StaticNested nested = new TargetClass.StaticNested();protected int val = nested.field;val = 1;Object var = val;
for (TargetClass target : targets) {if (target == null) {break;}var = target;}
Runnable anon1 = new Runnable() {public void run() {}};Runnable anon2 = new Runnable() {public void run() {}};
return var;}}
abstract class TargetClass {static class StaticNested {int field;}}