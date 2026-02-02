package test;
strictfp class SourceClass {int outerField;
class SourceInner {private TargetClass methodToMove(TargetClass.Inner param) {try {int var = outerField;SourceClass.this.outerField = 5;param.innerField = var;return new TargetClass.Inner();} catch (Exception e) {e.printStackTrace();return null;}}}}
abstract class TargetClass {static class Inner {int innerField;}}