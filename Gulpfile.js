var gulp = require("gulp"),
    mainBowerFiles = require('main-bower-files'),
    rename = require("gulp-rename"),
    inject = require("gulp-inject");


gulp.task("vendor:copy", [], function () {
    return gulp.src(mainBowerFiles(), {read: true, "base" : "."})
        .pipe(gulp.dest("src/main/resources/webroot/"));
});

gulp.task("vendor:inject", ["vendor:copy"], function () {
    return gulp.src("src/main/resources/webroot/index.html")
        .pipe(inject(gulp.src(mainBowerFiles(), {read: false}), {starttag: '<!-- inject:vendor:{{ext}} -->'}))
        .pipe(rename("index.html"))
        .pipe(gulp.dest("src/main/resources/webroot"));
});

gulp.task("default", ["vendor:inject"], function () {
    return gulp.src("src/main/resources/webroot/index.html")
        .pipe(inject(gulp.src(["!src/main/resources/webroot/bower_components/**/*.js", "src/main/resources/webroot/**/*.js"], {read: false}), {ignorePath: "/src/main/resources/webroot/"}))
        .pipe(rename("index.html"))
        .pipe(gulp.dest("src/main/resources/webroot"));
});
