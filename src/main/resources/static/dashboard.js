/**
 * ============================================================
 * 🏥 Dreams EMR - Hospital ERP Admin Dashboard v6.0
 * Features: Sidebar, Charts, Counters, Calendar, Ripple, Fullscreen
 * ============================================================
 */

(function () {
    'use strict';

    // ============================================================
    // 1. SIDEBAR - Mobile Toggle & Submenu
    // ============================================================
    function initSidebar() {
        const sidebar = document.getElementById('mainSidebar');
        const toggleBtn = document.getElementById('mobileSidebarToggle');
        const overlay = document.getElementById('sidebarOverlay');
        if (!sidebar || !toggleBtn || !overlay) return;

        function show() {
            sidebar.classList.add('mobile-open');
            overlay.classList.add('show');
            document.body.style.overflow = 'hidden';
        }
        function hide() {
            sidebar.classList.remove('mobile-open');
            overlay.classList.remove('show');
            document.body.style.overflow = '';
        }

        toggleBtn.addEventListener('click', function () {
            sidebar.classList.contains('mobile-open') ? hide() : show();
        });
        overlay.addEventListener('click', hide);
        document.addEventListener('keydown', function (e) {
            if (e.key === 'Escape' && sidebar.classList.contains('mobile-open')) hide();
        });

        // Submenu toggle
        document.querySelectorAll('.sidebar-item.has-submenu').forEach(function (item) {
            item.addEventListener('click', function (e) {
                e.preventDefault();
                var submenu = this.nextElementSibling;
                if (submenu && submenu.classList.contains('submenu')) {
                    this.classList.toggle('open');
                    submenu.classList.toggle('open');
                }
            });
        });
    }

    // ============================================================
    // 2. ACTIVE SIDEBAR LINK
    // ============================================================
    function setActiveSidebarLink() {
        var currentPath = window.location.pathname;
        document.querySelectorAll('#sidebarNav .sidebar-item').forEach(function (item) {
            item.classList.remove('active');
            var href = item.getAttribute('href');
            if (href && (currentPath === href || currentPath.startsWith(href + '/'))) {
                item.classList.add('active');
            }
        });
        if (!document.querySelector('#sidebarNav .sidebar-item.active')) {
            var dash = document.querySelector('#sidebarNav a[href="/admin/dashboard"]');
            if (dash) dash.classList.add('active');
        }
    }

    // ============================================================
    // 3. ANIMATED COUNTERS
    // ============================================================
    function animateCounters() {
        document.querySelectorAll('.counter').forEach(function (el) {
            var target = parseInt(el.getAttribute('data-target'), 10);
            var finalVal = target > 0 ? target : parseInt(el.textContent.trim().replace(/,/g, ''), 10);
            if (isNaN(finalVal) || finalVal === 0) {
                el.textContent = '0';
                return;
            }
            el.textContent = '0';
            var duration = 1200;
            var startTime = performance.now();
            function step(now) {
                var progress = Math.min((now - startTime) / duration, 1);
                var eased = 1 - Math.pow(1 - progress, 3);
                el.textContent = Math.floor(eased * finalVal).toLocaleString();
                if (progress < 1) requestAnimationFrame(step);
                else el.textContent = finalVal.toLocaleString();
            }
            requestAnimationFrame(step);
        });
    }

    // ============================================================
    // 4. CALENDAR WIDGET
    // ============================================================
    function initCalendar() {
        var grid = document.querySelector('.cal-grid');
        var monthYearEl = document.getElementById('calendarMonthYear');
        if (!grid || !monthYearEl) return;

        var now = new Date();
        var currentMonth = now.getMonth();
        var currentYear = now.getFullYear();
        var events = { 3: true, 7: true, 12: true, 18: true, 22: true, 25: true, 28: true };

        function render(month, year) {
            var monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
            monthYearEl.textContent = monthNames[month] + ' ' + year;

            var firstDay = new Date(year, month, 1).getDay();
            var daysInMonth = new Date(year, month + 1, 0).getDate();
            var today = new Date();
            var isCurrent = today.getMonth() === month && today.getFullYear() === year;

            var html = '';
            ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'].forEach(function (d) {
                html += '<div class="cal-day cal-day-label">' + d + '</div>';
            });
            for (var i = 0; i < firstDay; i++) html += '<div class="cal-day"></div>';
            for (var day = 1; day <= daysInMonth; day++) {
                var cls = 'cal-day';
                if (isCurrent && day === today.getDate()) cls += ' today';
                if (events[day]) cls += ' has-event';
                html += '<div class="' + cls + '">' + day + '</div>';
            }
            grid.innerHTML = html;
        }

        render(currentMonth, currentYear);

        window.changeMonth = function (delta) {
            currentMonth += delta;
            if (currentMonth > 11) { currentMonth = 0; currentYear++; }
            else if (currentMonth < 0) { currentMonth = 11; currentYear--; }
            render(currentMonth, currentYear);
        };
    }

    // ============================================================
    // 5. RIPPLE EFFECT
    // ============================================================
    function initRipple() {
        document.querySelectorAll('[data-ripple]').forEach(function (el) {
            el.style.position = 'relative';
            el.style.overflow = 'hidden';
            el.addEventListener('click', function (e) {
                var rect = this.getBoundingClientRect();
                var size = Math.max(rect.width, rect.height);
                var ripple = document.createElement('span');
                ripple.className = 'ripple';
                ripple.style.width = ripple.style.height = size + 'px';
                ripple.style.left = (e.clientX - rect.left - size / 2) + 'px';
                ripple.style.top = (e.clientY - rect.top - size / 2) + 'px';
                this.appendChild(ripple);
                setTimeout(function () { ripple.remove(); }, 600);
            });
        });
    }

    // ============================================================
    // 6. FULLSCREEN TOGGLE
    // ============================================================
    function initFullscreen() {
        var btn = document.getElementById('fullscreenBtn');
        if (!btn) return;
        btn.addEventListener('click', function () {
            if (!document.fullscreenElement) {
                document.documentElement.requestFullscreen();
                this.innerHTML = '<i class="fas fa-compress"></i>';
            } else {
                document.exitFullscreen();
                this.innerHTML = '<i class="fas fa-expand"></i>';
            }
        });
    }

    // ============================================================
    // 7. SPARKLINE CHARTS (Mini inline SVG charts)
    // ============================================================
    function drawSparkline(canvasId, data, color) {
        var canvas = document.getElementById(canvasId);
        if (!canvas) return;
        var ctx = canvas.getContext('2d');
        var w = canvas.width, h = canvas.height;
        var max = Math.max.apply(null, data);
        var min = Math.min.apply(null, data);
        var range = max - min || 1;
        var padding = 2;

        ctx.clearRect(0, 0, w, h);
        ctx.beginPath();
        ctx.strokeStyle = color;
        ctx.lineWidth = 1.5;
        ctx.lineJoin = 'round';

        data.forEach(function (val, i) {
            var x = padding + (i / (data.length - 1)) * (w - 2 * padding);
            var y = h - padding - ((val - min) / range) * (h - 2 * padding);
            if (i === 0) ctx.moveTo(x, y);
            else ctx.lineTo(x, y);
        });
        ctx.stroke();

        // Fill gradient
        var grad = ctx.createLinearGradient(0, 0, 0, h);
        grad.addColorStop(0, color.replace(')', ', 0.20)').replace('rgb', 'rgba'));
        grad.addColorStop(1, color.replace(')', ', 0.01)').replace('rgb', 'rgba'));
        ctx.lineTo(w - padding, h - padding);
        ctx.lineTo(padding, h - padding);
        ctx.closePath();
        ctx.fillStyle = grad;
        ctx.fill();
    }

    function initSparklines() {
        drawSparkline('sparkline1', [10, 18, 14, 22, 20, 28, 24, 32, 28, 35, 30, 38], '#2563EB');
        drawSparkline('sparkline2', [25, 20, 30, 28, 35, 32, 40, 38, 45, 42, 48, 50], '#F97316');
        drawSparkline('sparkline3', [5, 8, 6, 12, 10, 15, 13, 18, 16, 20, 18, 22], '#7C3AED');
        drawSparkline('sparkline4', [40, 38, 45, 42, 52, 48, 58, 55, 62, 58, 65, 60], '#EC4899');
    }

    // ============================================================
    // 8. BAR CHART - Patient Statistics
    // ============================================================
    function initBarChart() {
        var canvas = document.getElementById('patientChart');
        if (!canvas || typeof Chart === 'undefined') return;

        var ctx = canvas.getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                datasets: [{
                    label: 'New Patients',
                    data: [65, 78, 55, 92, 85, 70, 98, 110, 88, 95, 72, 108],
                    backgroundColor: '#2563EB',
                    borderRadius: 6,
                    barPercentage: 0.35,
                }, {
                    label: 'Old Patients',
                    data: [45, 52, 38, 65, 58, 48, 72, 80, 62, 70, 50, 75],
                    backgroundColor: '#06B6D4',
                    borderRadius: 6,
                    barPercentage: 0.35,
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: true,
                        position: 'top',
                        labels: { usePointStyle: true, pointStyle: 'circle', padding: 16, font: { size: 11, family: "'Inter', sans-serif" }, color: '#64748B' }
                    },
                    tooltip: {
                        backgroundColor: '#1E293B',
                        titleColor: '#f8fafc',
                        bodyColor: '#cbd5e1',
                        padding: 12,
                        cornerRadius: 10,
                    }
                },
                scales: {
                    x: { grid: { display: false }, ticks: { color: '#94A3B8', font: { size: 10 } } },
                    y: { grid: { color: 'rgba(148, 163, 184, 0.10)' }, ticks: { color: '#94A3B8', font: { size: 10 }, stepSize: 20 }, beginAtZero: true }
                }
            }
        });
    }

    // ============================================================
    // 9. PERIOD BUTTONS (Weekly/Monthly/Yearly)
    // ============================================================
    function initPeriodButtons() {
        document.querySelectorAll('.period-btn').forEach(function (btn) {
            btn.addEventListener('click', function () {
                var parent = this.closest('.period-btns');
                if (parent) parent.querySelectorAll('.period-btn').forEach(function (b) { b.classList.remove('active'); });
                this.classList.add('active');
            });
        });
    }

    // ============================================================
    // 10. TOAST NOTIFICATION
    // ============================================================
    window.showToast = function (message, type) {
        type = type || 'success';
        var existing = document.querySelector('.custom-toast');
        if (existing) existing.remove();

        var toast = document.createElement('div');
        toast.className = 'custom-toast';
        var iconMap = { success: 'fa-check-circle', error: 'fa-exclamation-circle', warning: 'fa-exclamation-triangle', info: 'fa-info-circle' };
        var colorMap = { success: '#10B981', error: '#EF4444', warning: '#F97316', info: '#2563EB' };
        toast.innerHTML = '<i class="fas ' + (iconMap[type] || iconMap.success) + '" style="color:' + (colorMap[type] || colorMap.success) + ';font-size:1.2rem;"></i><span>' + message + '</span>';
        toast.style.cssText = 'position:fixed;bottom:24px;right:24px;z-index:9999;background:#1E293B;border-radius:12px;padding:12px 20px;color:#f8fafc;font-size:0.85rem;box-shadow:0 8px 32px rgba(0,0,0,0.20);display:flex;align-items:center;gap:12px;transform:translateY(20px);opacity:0;transition:all 0.4s cubic-bezier(0.4,0,0.2,1);max-width:380px;font-family:Inter,sans-serif;';
        document.body.appendChild(toast);
        requestAnimationFrame(function () { toast.style.transform = 'translateY(0)'; toast.style.opacity = '1'; });
        setTimeout(function () {
            toast.style.transform = 'translateY(20px)';
            toast.style.opacity = '0';
            setTimeout(function () { if (toast.parentNode) toast.remove(); }, 400);
        }, 3500);
    };

    // ============================================================
    // 11. LOGOUT CONFIRM
    // ============================================================
    function initLogout() {
        document.querySelectorAll('#logoutBtn').forEach(function (btn) {
            if (!btn) return;
            btn.addEventListener('click', function (e) {
                if (!confirm('Are you sure you want to logout?')) e.preventDefault();
            });
        });
    }

    // ============================================================
    // 12. SEARCH
    // ============================================================
    function initSearch() {
        var input = document.getElementById('globalSearch');
        if (!input) return;
        input.addEventListener('keydown', function (e) {
            if (e.key === 'Enter' && this.value.trim().length > 0) {
                showToast('Searching for "' + this.value.trim() + '"...', 'info');
            }
        });
    }

    // ============================================================
    // INIT
    // ============================================================
    function init() {
        setActiveSidebarLink();
        animateCounters();
        initCalendar();
        initRipple();
        initFullscreen();
        initSparklines();
        initPeriodButtons();
        initLogout();
        initSearch();

        if (typeof Chart !== 'undefined') {
            initBarChart();
        }
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', function () {
            initSidebar();
            init();
        });
    } else {
        initSidebar();
        init();
    }
})();

