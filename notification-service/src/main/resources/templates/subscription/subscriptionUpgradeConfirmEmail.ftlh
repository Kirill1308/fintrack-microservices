<#ftl encoding="UTF-8">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Subscription Upgrade Confirmation</title>
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
    <h1 class="email-header">Subscription Upgrade Confirmed</h1>
    <div class="email-content">
        <p class="email-text">Hello ${name},</p>
        <p class="email-text">Your subscription has been successfully upgraded to the ${tier} tier! Enjoy the additional benefits of your new plan.</p>
    </div>
    <div class="email-footer">
        <p class="email-contact">
            Questions? <a href="mailto:support@example.com" class="email-support-link">Contact our support team</a>.
        </p>
    </div>
</div>
</body>
</html>
