/**
 * ============================================================
 * 🏥 Hospital ERP - Auth Pages JavaScript
 * Features: Password Toggle, Ripple, Animations, Form Validation,
 *           Password Strength Meter, Photo Preview, Loading States
 * Pages: Login, Register, Forgot Password
 * ============================================================
 */

(function () {
    'use strict';

    // ============================================================
    // 1. PASSWORD VISIBILITY TOGGLE
    // ============================================================
    function initPasswordToggle() {
        document.querySelectorAll('.toggle-password').forEach(function (btn) {
            btn.addEventListener('click', function () {
                var wrapper = this.closest('.input-group-glass');
                if (!wrapper) wrapper = this.closest('.input-group-register');
                var input = wrapper ? wrapper.querySelector('.form-control-glass, .form-control-register') : null;
                if (!input) return;

                var type = input.getAttribute('type') === 'password' ? 'text' : 'password';
                input.setAttribute('type', type);

                var icon = this.querySelector('i');
                if (icon) {
                    icon.classList.toggle('fa-eye');
                    icon.classList.toggle('fa-eye-slash');
                }
            });
        });
    }

    // ============================================================
    // 2. RIPPLE EFFECT ON BUTTONS
    // ============================================================
    function initRipple() {
        document.querySelectorAll('.btn-login-primary, .btn-login-cancel, .btn-register-primary, .btn-register-reset').forEach(function (btn) {
            btn.addEventListener('click', function (e) {
                var rect = this.getBoundingClientRect();
                var size = Math.max(rect.width, rect.height);
                var ripple = document.createElement('span');
                ripple.className = 'ripple-effect';
                ripple.style.width = ripple.style.height = size + 'px';
                ripple.style.left = (e.clientX - rect.left - size / 2) + 'px';
                ripple.style.top = (e.clientY - rect.top - size / 2) + 'px';
                this.appendChild(ripple);
                setTimeout(function () {
                    if (ripple.parentNode) ripple.remove();
                }, 600);
            });
        });
    }

    // ============================================================
    // 3. STAGGER ANIMATION
    // ============================================================
    function initStaggerAnimations() {
        document.querySelectorAll('.stagger-item').forEach(function (el, index) {
            setTimeout(function () {
                el.classList.add('visible');
            }, 150 + index * 80);
        });
    }

    // ============================================================
    // 4. LOGIN FORM VALIDATION
    // ============================================================
    function initLoginValidation() {
        var form = document.getElementById('loginForm');
        if (!form) return;

        form.addEventListener('submit', function (e) {
            var username = document.getElementById('username');
            var password = document.getElementById('password');
            var valid = true;

            clearErrors(form);

            if (!username || !username.value || username.value.trim() === '') {
                showFieldError(username, 'Please enter your username or email');
                valid = false;
            }

            if (!password || !password.value || password.value === '') {
                showFieldError(password, 'Please enter your password');
                valid = false;
            }

            if (!valid) {
                e.preventDefault();
            } else {
                showLoading(form.querySelector('.btn-login-primary'));
            }
        });
    }

    // ============================================================
    // 5. FORGOT PASSWORD FORM VALIDATION
    // ============================================================
    function initForgotPasswordValidation() {
        var form = document.getElementById('forgotPasswordForm');
        if (!form) return;

        form.addEventListener('submit', function (e) {
            var email = document.getElementById('forgotEmail');
            var valid = true;

            clearErrors(form);

            if (!email || !email.value || email.value.trim() === '') {
                showFieldError(email, 'Please enter your email address');
                valid = false;
            } else if (!isValidEmail(email.value)) {
                showFieldError(email, 'Please enter a valid email address');
                valid = false;
            }

            if (!valid) {
                e.preventDefault();
            } else {
                showLoading(form.querySelector('.btn-login-primary'));
            }
        });
    }

    // ============================================================
    // 6. REGISTER FORM VALIDATION
    // ============================================================
    function initRegisterValidation() {
        var form = document.getElementById('registerForm');
        if (!form) return;

        form.addEventListener('submit', function (e) {
            var valid = true;
            clearErrors(form);

            // First Name
            var firstName = document.getElementById('firstName');
            if (!firstName || !firstName.value || firstName.value.trim().length < 2) {
                showFieldError(firstName, 'First name must be at least 2 characters');
                valid = false;
            }

            // Last Name
            var lastName = document.getElementById('lastName');
            if (!lastName || !lastName.value || lastName.value.trim().length < 2) {
                showFieldError(lastName, 'Last name must be at least 2 characters');
                valid = false;
            }

            // Username
            var username = document.getElementById('regUsername');
            if (!username || !username.value || username.value.trim().length < 3) {
                showFieldError(username, 'Username must be at least 3 characters');
                valid = false;
            } else if (!/^[a-zA-Z0-9_]+$/.test(username.value)) {
                showFieldError(username, 'Username can only contain letters, numbers, and underscores');
                valid = false;
            }

            // Email
            var email = document.getElementById('email');
            if (!email || !email.value || !isValidEmail(email.value)) {
                showFieldError(email, 'Please enter a valid email address');
                valid = false;
            }

            // Mobile
            var mobile = document.getElementById('mobileNumber');
            if (!mobile || !mobile.value || !/^[0-9+\-\s()]{7,20}$/.test(mobile.value)) {
                showFieldError(mobile, 'Please enter a valid mobile number');
                valid = false;
            }

            // Gender
            var gender = document.getElementById('gender');
            if (!gender || !gender.value) {
                showFieldError(gender, 'Please select your gender');
                valid = false;
            }

            // Date of Birth
            var dob = document.getElementById('dateOfBirth');
            if (!dob || !dob.value) {
                showFieldError(dob, 'Please enter your date of birth');
                valid = false;
            }

            // Blood Group
            var bloodGroup = document.getElementById('bloodGroup');
            if (!bloodGroup || !bloodGroup.value) {
                showFieldError(bloodGroup, 'Please select your blood group');
                valid = false;
            }

            // Address
            var address = document.getElementById('address');
            if (!address || !address.value || address.value.trim().length < 5) {
                showFieldError(address, 'Please enter your address');
                valid = false;
            }

            // Password
            var password = document.getElementById('regPassword');
            if (!password || !password.value || password.value.length < 6) {
                showFieldError(password, 'Password must be at least 6 characters');
                valid = false;
            }

            // Confirm Password
            var confirmPassword = document.getElementById('confirmPassword');
            if (!confirmPassword || !confirmPassword.value) {
                showFieldError(confirmPassword, 'Please confirm your password');
                valid = false;
            } else if (password && password.value !== confirmPassword.value) {
                showFieldError(confirmPassword, 'Passwords do not match');
                valid = false;
            }

            // Role
            var role = document.getElementById('role');
            if (!role || !role.value) {
                var roleFeedback = document.querySelector('.role-selection .invalid-feedback');
                if (roleFeedback) {
                    roleFeedback.textContent = 'Please select a role';
                    roleFeedback.style.display = 'block';
                }
                valid = false;
            }

            // Terms
            var agree = document.getElementById('agreeToTerms');
            if (!agree || !agree.checked) {
                var termsFeedback = document.querySelector('.form-check-terms .invalid-feedback');
                if (termsFeedback) {
                    termsFeedback.textContent = 'You must agree to the terms and conditions';
                    termsFeedback.style.display = 'block';
                }
                valid = false;
            }

            if (!valid) {
                e.preventDefault();
            } else {
                showLoading(form.querySelector('.btn-register-primary'));
            }
        });
    }

    // ============================================================
    // 7. PASSWORD STRENGTH METER
    // ============================================================
    function initPasswordStrength() {
        var passwordInput = document.getElementById('regPassword');
        if (!passwordInput) return;

        passwordInput.addEventListener('input', function () {
            var password = this.value;
            var strengthLevel = document.getElementById('strengthLevel');
            var strengthText = document.getElementById('strengthText');
            if (!strengthLevel || !strengthText) return;

            strengthLevel.className = 'strength-level';

            if (password.length === 0) {
                strengthLevel.style.width = '0%';
                strengthText.textContent = 'Enter a password';
                strengthText.className = 'strength-text';
                return;
            }

            var score = 0;
            if (password.length >= 6) score++;
            if (password.length >= 10) score++;
            if (password.length >= 14) score++;
            if (/[a-z]/.test(password)) score++;
            if (/[A-Z]/.test(password)) score++;
            if (/[0-9]/.test(password)) score++;
            if (/[^a-zA-Z0-9]/.test(password)) score++;

            if (score <= 2) {
                strengthLevel.classList.add('weak');
                strengthText.textContent = 'Weak';
                strengthText.className = 'strength-text weak';
            } else if (score <= 4) {
                strengthLevel.classList.add('medium');
                strengthText.textContent = 'Medium';
                strengthText.className = 'strength-text medium';
            } else if (score <= 5) {
                strengthLevel.classList.add('strong');
                strengthText.textContent = 'Strong';
                strengthText.className = 'strength-text strong';
            } else {
                strengthLevel.classList.add('very-strong');
                strengthText.textContent = 'Very Strong';
                strengthText.className = 'strength-text very-strong';
            }
        });
    }

    // ============================================================
    // 8. CONFIRM PASSWORD MATCH CHECK
    // ============================================================
    function initPasswordMatch() {
        var password = document.getElementById('regPassword');
        var confirmPassword = document.getElementById('confirmPassword');
        var feedback = document.getElementById('passwordMatchFeedback');
        if (!password || !confirmPassword || !feedback) return;

        function checkMatch() {
            if (confirmPassword.value.length === 0) {
                feedback.textContent = '';
                feedback.className = 'password-match-feedback';
                return;
            }
            if (password.value === confirmPassword.value) {
                feedback.textContent = '✓ Passwords match';
                feedback.className = 'password-match-feedback match';
            } else {
                feedback.textContent = '✗ Passwords do not match';
                feedback.className = 'password-match-feedback no-match';
            }
        }

        password.addEventListener('input', checkMatch);
        confirmPassword.addEventListener('input', checkMatch);
    }

    // ============================================================
    // 9. PROFILE PHOTO PREVIEW
    // ============================================================
    function initPhotoUpload() {
        var photoUpload = document.getElementById('photoUpload');
        var photoInput = document.getElementById('photoInput');
        var photoPreview = document.getElementById('photoPreview');
        if (!photoUpload || !photoInput || !photoPreview) return;

        photoUpload.addEventListener('click', function () {
            photoInput.click();
        });

        photoInput.addEventListener('change', function (e) {
            var file = e.target.files[0];
            if (file) {
                var reader = new FileReader();
                reader.onload = function (event) {
                    photoPreview.innerHTML = '<img src="' + event.target.result + '" alt="Profile Photo">';
                    photoPreview.classList.add('has-image');
                };
                reader.readAsDataURL(file);
            }
        });
    }

    // ============================================================
    // 10. ROLE CARD SELECTION (global wrapper)
    // ============================================================
    function initRoleSelection() {
        document.querySelectorAll('.role-card').forEach(function (card) {
            card.addEventListener('click', function () {
                document.querySelectorAll('.role-card').forEach(function (c) {
                    c.classList.remove('active');
                });
                this.classList.add('active');
                var roleInput = document.getElementById('role');
                if (roleInput) {
                    roleInput.value = this.getAttribute('data-role');
                }
                var roleFeedback = document.querySelector('.role-selection .invalid-feedback');
                if (roleFeedback) roleFeedback.style.display = 'none';
            });
        });
    }

    // ============================================================
    // 11. CANCEL / RESET BUTTONS
    // ============================================================
    function initCancelButton() {
        var cancelBtn = document.getElementById('cancelBtn');
        if (cancelBtn) {
            cancelBtn.addEventListener('click', function () {
                var form = document.getElementById('loginForm');
                if (form) {
                    form.querySelectorAll('.form-control-glass').forEach(function (input) {
                        input.value = '';
                        input.classList.remove('is-invalid');
                    });
                    clearErrors(form);
                }
            });
        }

        var resetBtn = document.getElementById('resetBtn');
        if (resetBtn) {
            resetBtn.addEventListener('click', function () {
                var form = document.getElementById('registerForm');
                if (form) {
                    form.querySelectorAll('.form-control-register').forEach(function (input) {
                        input.value = '';
                        input.classList.remove('is-invalid');
                    });
                    clearErrors(form);
                    document.querySelectorAll('.role-card').forEach(function (c) {
                        c.classList.remove('active');
                    });
                    var roleInput = document.getElementById('role');
                    if (roleInput) roleInput.value = '';
                    var photoPreview = document.getElementById('photoPreview');
                    if (photoPreview) {
                        photoPreview.innerHTML = '<i class="fas fa-camera"></i><span>Upload Photo</span>';
                        photoPreview.classList.remove('has-image');
                    }
                    var strengthLevel = document.getElementById('strengthLevel');
                    var strengthText = document.getElementById('strengthText');
                    if (strengthLevel) {
                        strengthLevel.className = 'strength-level';
                        strengthLevel.style.width = '0%';
                    }
                    if (strengthText) {
                        strengthText.textContent = 'Enter a password';
                        strengthText.className = 'strength-text';
                    }
                    var feedback = document.getElementById('passwordMatchFeedback');
                    if (feedback) {
                        feedback.textContent = '';
                        feedback.className = 'password-match-feedback';
                    }
                }
            });
        }
    }

    // ============================================================
    // 12. ESCAPE KEY HANDLER
    // ============================================================
    function initEscapeHandler() {
        document.addEventListener('keydown', function (e) {
            if (e.key === 'Escape') {
                var active = document.activeElement;
                if (active && (active.classList.contains('form-control-glass') || active.classList.contains('form-control-register'))) {
                    active.blur();
                }
            }
        });
    }

    // ============================================================
    // 13. AUTO-FOCUS
    // ============================================================
    function initAutoFocus() {
        var field = document.getElementById('username') || document.getElementById('forgotEmail');
        if (field) {
            setTimeout(function () { field.focus(); }, 900);
        }
    }

    // ============================================================
    // UTILITY FUNCTIONS
    // ============================================================
    function showFieldError(input, message) {
        if (!input) return;
        input.classList.add('is-invalid');
        var feedback = input.closest('.mb-3') ? input.closest('.mb-3').querySelector('.invalid-feedback') : null;
        if (feedback) {
            feedback.textContent = message;
            feedback.style.display = 'block';
        } else {
            // fallback: create inline error
            var div = document.createElement('div');
            div.className = 'invalid-feedback';
            div.textContent = message;
            div.style.display = 'block';
            input.parentNode.appendChild(div);
        }
    }

    function clearErrors(form) {
        if (!form) return;
        form.querySelectorAll('.is-invalid').forEach(function (el) {
            el.classList.remove('is-invalid');
        });
        form.querySelectorAll('.invalid-feedback').forEach(function (el) {
            el.textContent = '';
            el.style.display = 'none';
        });
    }

    function showLoading(btn) {
        if (!btn) return;
        var text = btn.querySelector('.btn-text');
        var loading = btn.querySelector('.btn-loading');
        if (text && loading) {
            text.classList.add('d-none');
            loading.classList.remove('d-none');
            btn.disabled = true;
        }
    }

    function isValidEmail(email) {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }

    // ============================================================
    // GLOBAL EXPOSURE: selectRole for inline onclick
    // ============================================================
    window.selectRole = function (el, role) {
        document.querySelectorAll('.role-card').forEach(function (c) {
            c.classList.remove('active');
        });
        el.classList.add('active');
        var roleInput = document.getElementById('role');
        if (roleInput) roleInput.value = role;
        var roleFeedback = document.querySelector('.role-selection .invalid-feedback');
        if (roleFeedback) roleFeedback.style.display = 'none';
    };

    // ============================================================
    // INIT
    // ============================================================
    function init() {
        initPasswordToggle();
        initRipple();
        initStaggerAnimations();
        initLoginValidation();
        initForgotPasswordValidation();
        initRegisterValidation();
        initPasswordStrength();
        initPasswordMatch();
        initPhotoUpload();
        initRoleSelection();
        initCancelButton();
        initEscapeHandler();
        initAutoFocus();
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', init);
    } else {
        init();
    }

})();

