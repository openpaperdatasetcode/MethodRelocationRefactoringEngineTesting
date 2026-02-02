package test;
// Target enum class (modifier is empty)enum TargetEnum {INSTANCE;
// Target feature: static nested classstatic class TargetStaticNested {int superField; // super.field for LabeledStatement}}
// Source enum class (public modifier + type parameter)public enum SourceEnum<T> {INSTANCE;
// Source inner record (source_inner_rec)record SourceInnerRec<T>(T val) {}
// Normal method (public access modifier + base type return)public int methodToMove(TargetEnum target, SourceInnerRec<T> rec) {// Variable callT var = rec.val();TargetEnum.TargetStaticNested nested = new TargetEnum.TargetStaticNested();
// Constructor invocationSourceInnerRec<T> newRec = new SourceInnerRec<>(var);
// Super keywords (call enum superclass method)super.toString();
// Uses outer this (enum instance reference)SourceEnum<T> outer = SourceEnum.this;
// LabeledStatement (public modifier + super.field = 3, pos: source)sourceLabel: {nested.superField = 3;if (nested.superField == 3) break sourceLabel;}
return nested.superField;}}