<#ftl encoding="UTF-8">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thank You for Registering, ${name}</title>
    <style>
        body.email-body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .email-container {
            max-width: 600px;
            margin: 20px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .email-header {
            text-align: center;
            padding-bottom: 20px;
            border-bottom: 2px solid #f0f0f0;
            margin-bottom: 30px;
        }

        .email-content {
            margin-bottom: 30px;
        }

        .email-text {
            margin: 0 0 20px 0;
            font-size: 16px;
            color: #333333 !important;
        }

        .email-button {
            display: inline-block;
            padding: 12px 24px;
            background-color: #3498db;
            color: #ffffff !important;
            text-decoration: none;
            border-radius: 4px;
            margin: 20px 0;
            font-weight: bold;
            text-align: center;
        }

        .email-footer {
            margin-top: 30px;
            padding-top: 20px;
            border-top: 2px solid #f0f0f0;
            font-size: 14px;
            color: #666666;
        }

        .email-contact {
            margin: 0;
        }

        .email-support-link {
            color: #3498db;
            text-decoration: none;
        }
    </style>
</head>
<body class="email-body">
<div class="email-container">
    <h1 class="email-header">Welcome, ${name}!</h1>
    <div class="email-content">
        <p class="email-text">We are excited to welcome you to FinTrack! Start tracking and managing your finances
            easily
            and conveniently.</p>

        <a href="https://your-login-page.com" class="email-button">Log In to Your Account</a>

    </div>
    <div class="email-footer">
        <p class="email-contact">
            If you have any questions or need further assistance, feel free to
            <a href="mailto:support@example.com" class="email-support-link">contact our support team</a>.
        </p>
    </div>
</div>
</body>
</html>
