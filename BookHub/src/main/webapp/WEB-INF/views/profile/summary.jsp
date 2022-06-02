<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>${username}</title>
        <link rel="stylesheet" href="/static/css/profile/profile.css">
        <link rel="stylesheet" href="/static/css/profile/summary.css">
    </head>

    <body>
        <!-- Header -->

        <!-- Profile -->
        <div id="profile-wrapper">
            <!-- Profile Head -->
            <div id="profile-head">
                <h1 id="profile-username">
                    ${username}
                </h1>
            </div>
            <!-- Profile Tab -->
            <div id="profile-tabmenu">
                <a class="tabmenu-btn activetab" href="/profile/${username}">
                    📋 활동 요약
                </a>
                <a class="tabmenu-btn" href="/profile/${username}/library">
                    📚 내 서재
                </a>
            </div>

            <!-- Profile Summary -->
            <div id="profile-body">
                <div id="summary-calendar" class="profile-section">
                    <h2 class="profile-section-title">
                        나의 독서 활동
                    </h2>
                    <div id="calendar-graph">

                    </div>
                    <div id="calendar-stat">
                        <div class="stat-content">

                        </div>
                        <div class="stat-content">

                        </div>
                        <div class="stat-content">

                        </div>
                    </div>
                </div>
                <div id="summary-recent">
                    <div id="summary-recent-book" class="profile-section">
                        <h2 class="profile-section-title">
                            최근에 읽은 책
                        </h2>
                        <div class="recent-content">
                            <div class="recent-item">

                            </div>
                            <div class="recent-item">

                            </div>
                        </div>
                    </div>
                    <div id="summary-recent-review" class="profile-section">
                        <h2 class="profile-section-title">
                            최근에 남긴 리뷰
                        </h2>
                        <div class="recent-content">
                            <div class="recent-item">

                            </div>
                            <div class="recent-item">

                            </div>
                            <div class="recent-item">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </body>

    </html>